package org.example.hibernate.session20.controller;


import org.example.hibernate.ra.orm.entity.Seed;
import org.example.hibernate.session20.service.CloudinaryService;
import org.example.hibernate.session20.service.SeedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@Controller
public class SeedController {

    @Autowired
    private SeedService seedService;

    @Autowired
    private CloudinaryService cloudinaryService;

    @GetMapping("/seed")
    public String showSeed(Model model) {
        model.addAttribute("seeds", seedService.getAllSeed());
        return "seedList";
    }

    @GetMapping("/seedForm")
    public String seedForm(Model model) {
        model.addAttribute("seed", new Seed());
        return "seedForm";
    }

    @PostMapping("/seed")
    public String addSeed(@Valid @ModelAttribute("seed") Seed seed,
                          BindingResult result,
                          @RequestParam("imageFile") MultipartFile imageFile,
                          Model model) throws IOException {

        if (seedService.isSeedNameExists(seed.getSeedName())) {
            result.rejectValue("seedName", "error.seed", "Tên đã tồn tại");
        }

        if (result.hasErrors()) {
            return "seedForm";
        }

        String imageUrl = cloudinaryService.uploadFile(imageFile);
        seed.setImage(imageUrl);
        seedService.addSeed(seed);
        return "redirect:/seed";
    }

    @GetMapping("/seedEdit/{id}")
    public String editForm(@PathVariable int id, Model model) {
        Seed seed = seedService.getAllSeed().stream()
                .filter(s -> s.getId() == id).findFirst().orElse(null);
        model.addAttribute("seed", seed);
        return "seedEdit";
    }

    @PostMapping("/seedEdit")
    public String editSeed(@ModelAttribute("seed") Seed seed,
                           @RequestParam("imageFile") MultipartFile imageFile) throws IOException {

        if (!imageFile.isEmpty()) {
            String imageUrl = cloudinaryService.uploadFile(imageFile);
            seed.setImage(imageUrl);
        }

        seedService.editSeed(seed);
        return "redirect:/seed";
    }

    @GetMapping("/seedDelete/{id}")
    public String deleteSeed(@PathVariable int id) {
        Seed seed = seedService.getAllSeed().stream()
                .filter(s -> s.getId() == id).findFirst().orElse(null);
        seedService.deleteSeed(seed);
        return "redirect:/seed";
    }

    @GetMapping("/seed/search")
    public String searchSeed(@RequestParam(value = "name", required = false) String name,
                             @RequestParam(value = "minPrice", required = false) Integer minPrice,
                             @RequestParam(value = "maxPrice", required = false) Integer maxPrice,
                             Model model) {

        List<Seed> results = seedService.searchSeed(name, minPrice, maxPrice);
        model.addAttribute("seeds", results);
        return "seedList";
    }
}

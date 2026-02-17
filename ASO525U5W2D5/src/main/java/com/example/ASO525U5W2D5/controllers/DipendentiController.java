package com.example.ASO525U5W2D5.controllers;

import com.example.ASO525U5W2D5.entities.Dipendenti;
import com.example.ASO525U5W2D5.payloads.ModificaDipendenteDTO;
import com.example.ASO525U5W2D5.services.DipendentiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/dipendenti")
public class DipendentiController {
    private final DipendentiService dipendentiService;

    @Autowired
    public DipendentiController(DipendentiService dipendentiService) {
        this.dipendentiService = dipendentiService;
    }

    //il post con cui creo un nuovo Dipendente ora è nell'AuthController con endpoint /auth/register perché altrimenti con l'endpoint di questo controller la request avrebbe bisogno del token

    @GetMapping
    @PreAuthorize("hasAnyAuthority('SUPERADMIN', 'ADMIN')")
    public Page<Dipendenti> getAll(@RequestParam(defaultValue = "0") int page,
                                   @RequestParam(defaultValue = "10") int size,
                                   @RequestParam(defaultValue = "surname") String orderBy,
                                   @RequestParam(defaultValue = "asc") String sortCriteria) {
        return this.dipendentiService.findAll(page, size, orderBy, sortCriteria);
    }

    @GetMapping("/me")
    public Dipendenti getProfileInfo(@AuthenticationPrincipal Dipendenti currentAuthenticatedDip) {
        return this.dipendentiService.findById(currentAuthenticatedDip.getId());
    }

    @PutMapping("/me")
    public Dipendenti updateProfile(@AuthenticationPrincipal Dipendenti currentAuthenticatedDip, @RequestBody ModificaDipendenteDTO payload) {
        return this.dipendentiService.findAndModify(currentAuthenticatedDip.getId(), payload);
    }

    @DeleteMapping()
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProfile(@AuthenticationPrincipal Dipendenti currentAuthenticatedDip) {
        this.dipendentiService.findByIdAndDelete(currentAuthenticatedDip.getId());
    }

    @PatchMapping("/me/avatar")
    public void uploadImage(@RequestParam("profile_pic") MultipartFile file, @PathVariable long dipendenteId) {
        this.dipendentiService.uploadAvatar(file, dipendenteId);
    }
}

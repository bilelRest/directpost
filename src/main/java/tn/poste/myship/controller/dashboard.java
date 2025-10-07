package tn.poste.myship.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import tn.poste.myship.entity.Parcel;
import tn.poste.myship.repo.ParcelRepo;

import java.time.LocalDate;

@Controller
public class dashboard {

    @Controller
    public class SuiviController {

        @Autowired
        private ParcelRepo parcelRepo;

        @GetMapping("/suivie")
        public String suivie(
                @RequestParam(value = "dateDebut", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dateDebut,
                @RequestParam(value = "dateFin", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dateFin,
                @RequestParam(value = "page", defaultValue = "0") int page,
                Model model) {

            // Par défaut, afficher les 30 derniers jours
            if (dateDebut == null) {
                dateDebut = LocalDate.now().minusDays(30);
            }
            if (dateFin == null) {
                dateFin = LocalDate.now();
            }

            Pageable pageable = PageRequest.of(page, 10, Sort.by("trackingNumber.createdAt").descending());
            Page<Parcel> parcelsPage = parcelRepo.findByTrackingNumberCreatedAtBetween(dateDebut, dateFin, pageable);

            model.addAttribute("parcels", parcelsPage.getContent());
            model.addAttribute("dateDebut", dateDebut);
            model.addAttribute("dateFin", dateFin);
            model.addAttribute("page", page);
            model.addAttribute("totalPages", parcelsPage.getTotalPages());
            model.addAttribute("total", parcelsPage.getTotalElements());
            model.addAttribute("debut", page * 10 + 1);
            model.addAttribute("fin", Math.min((page + 1) * 10, parcelsPage.getTotalElements()));

            // Nouvelles statistiques sans status
            model.addAttribute("totalColis", parcelsPage.getTotalElements());
            model.addAttribute("colisAujourdhui", parcelRepo.countByTrackingNumberCreatedAt(LocalDate.now()));
            model.addAttribute("totalRevenue", parcelRepo.sumPriceByDateRange(dateDebut, dateFin));
            model.addAttribute("poidsTotal", parcelRepo.sumWeightByDateRange(dateDebut, dateFin));

            return "suivie";
        }
    }
    }


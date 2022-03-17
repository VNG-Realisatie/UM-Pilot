import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {Werkzoekende} from "../../entities/werkzoekende/werkzoekende";
import {ActivatedRoute} from "@angular/router";
import {Location} from "@angular/common";
import {WerkzoekendeBemiddelaarService} from "../../services/werkzoekende-bemiddelaar.service";
import {Subscription} from "rxjs";
import {PdfGeneratorService} from "../../services/pdf-generator.service";

@Component({
  selector: 'app-werkzoekende-screen-detailed',
  templateUrl: './werkzoekende-screen-detailed.component.html',
  styleUrls: ['./werkzoekende-screen-detailed.component.css']
})
export class WerkzoekendeScreenDetailedComponent implements OnInit {

  @ViewChild('detailedForm', {read: ElementRef, static: true})
  parentFormDetailedVacature?: ElementRef;

  werkzoekende?: Werkzoekende;
  loadingStatus: boolean = false;
  subscription: Subscription = new Subscription();


  constructor(
    private route: ActivatedRoute,
    private location: Location,
    private werkzoekendeService: WerkzoekendeBemiddelaarService,
    private pdfGenerator: PdfGeneratorService
  ) {
  }

  ngOnInit(): void {
    const vraagId = this.route.snapshot.paramMap.get('vraagId');
    const vumId = this.route.snapshot.paramMap.get('vumId');

    this.subscription = this.pdfGenerator.loadingStatus$.subscribe(x => this.loadingStatus = x);

    if (vraagId && vumId) {
      this.werkzoekendeService.getDetailVacature(vraagId, vumId)
        .subscribe(detailedWerkzoekende => this.werkzoekende = detailedWerkzoekende)
    }
  }

  goBack(): void {
    this.location.back();
  }

  generatePdf() {
    if (this.parentFormDetailedVacature) {
      this.pdfGenerator.generatePdf(this.parentFormDetailedVacature)
    }
  }

}

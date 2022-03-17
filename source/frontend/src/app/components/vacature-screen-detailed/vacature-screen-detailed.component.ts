import {Component, ElementRef, OnDestroy, OnInit, ViewChild} from '@angular/core';
import {VacatureBemiddelaarService} from "../../services/vacature-bemiddelaar.service";
import {Vacature} from "../../entities/vacature/vacature";
import {ActivatedRoute} from "@angular/router";
import {Location} from "@angular/common";
import {PdfGeneratorService} from "../../services/pdf-generator.service";
import {Subscription} from "rxjs";

@Component({
  selector: 'app-vacature-screen-detailed',
  templateUrl: './vacature-screen-detailed.component.html',
  styleUrls: ['./vacature-screen-detailed.component.css']
})
export class VacatureScreenDetailedComponent implements OnInit, OnDestroy {

  @ViewChild('detailedForm', {read: ElementRef, static: true})
  parentFormDetailedVacature?: ElementRef;

  vacature?: Vacature;
  loadingStatus: boolean = false;
  subscription: Subscription = new Subscription();

  constructor(
    private route: ActivatedRoute,
    private location: Location,
    private vacatureService: VacatureBemiddelaarService,
    private pdfGenerator: PdfGeneratorService) {
  }

  ngOnInit(): void {
    const vraagId = this.route.snapshot.paramMap.get('vraagId');
    const vumId = this.route.snapshot.paramMap.get('vumId');

    this.subscription = this.pdfGenerator.loadingStatus$.subscribe(x => this.loadingStatus = x);

    if (vraagId && vumId) {
      this.vacatureService.getDetailVacature(vraagId, vumId).subscribe(detailedVacature => {
        this.vacature = detailedVacature
      });
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

  ngOnDestroy(): void {
    this.subscription.unsubscribe()
  }

}

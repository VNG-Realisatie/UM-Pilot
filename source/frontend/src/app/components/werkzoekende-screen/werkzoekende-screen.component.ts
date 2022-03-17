import {Component, ElementRef, OnDestroy, OnInit, ViewChild} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {Location} from "@angular/common";
import {WerkzoekendePassService} from "../../services/werkzoekende-pass.service";
import {MPWerkzoekendeMatchBemiddelaar} from "../../entities/werkzoekende/mPWerkzoekendeMatchBemiddelaar";
import {Subscription} from "rxjs";
import {PdfGeneratorService} from "../../services/pdf-generator.service";

@Component({
  selector: 'app-werkzoekende-screen',
  templateUrl: './werkzoekende-screen.component.html',
  styleUrls: ['./werkzoekende-screen.component.css']
})
export class WerkzoekendeScreenComponent implements OnInit, OnDestroy {

  @ViewChild('formRef', {read: ElementRef, static: false}) set content(content: ElementRef) {
    if(content) { // initially setter gets called with undefined
      this.parentFormVacature = content;
    }
  }
  parentFormVacature?: ElementRef;

  werkzoekende?: MPWerkzoekendeMatchBemiddelaar
  loadingStatus: boolean = false;
  subscription: Subscription = new Subscription();
  vraagId?: string;
  vumId?: string;

  constructor(
    private werkzoekendePassService: WerkzoekendePassService,
    private route: ActivatedRoute,
    private location: Location,
    private pdfGenerator: PdfGeneratorService
  ) {
  }

  ngOnInit(): void {
    const vraagId = this.route.snapshot.paramMap.get('vraagId');
    const vumId = this.route.snapshot.paramMap.get('vumId');

    this.subscription = this.pdfGenerator.loadingStatus$.subscribe(x => this.loadingStatus = x);


    if (vraagId) {
      this.vraagId = vraagId;
    }
    if (vumId) {
      this.vumId = vumId;
    }
    this.werkzoekende = this.werkzoekendePassService.getWerkzoekende();
  }

  goBack(): void {
    this.location.back();
  }


  generatePdf() {
    if (this.parentFormVacature) {
      this.pdfGenerator.generatePdf(this.parentFormVacature)
    }
  }

  ngOnDestroy(): void {
    this.subscription.unsubscribe()
  }


}

import {Component, ElementRef, OnDestroy, OnInit, ViewChild} from '@angular/core';
import {VacaturePassService} from "../../services/vacature-pass.service";
import {MPVacatureMatch} from "../../entities/vacature/mPVacatureMatch";
import {ActivatedRoute} from "@angular/router";
import {Location} from "@angular/common";
import {PdfGeneratorService} from "../../services/pdf-generator.service";
import {Subscription} from "rxjs";

@Component({
  selector: 'app-vacature-screen',
  templateUrl: './vacature-screen.component.html',
  styleUrls: ['./vacature-screen.component.css']
})
export class VacatureScreenComponent implements OnInit, OnDestroy {

  @ViewChild('formRef', {read: ElementRef, static: false}) set content(content: ElementRef) {
    if(content) { // initially setter gets called with undefined
      this.parentFormVacature = content;
    }
  }
  parentFormVacature?: ElementRef;

  vacature?: MPVacatureMatch
  loadingStatus: boolean = false;
  subscription: Subscription = new Subscription();
  vraagId?: string;
  vumId?: string;

  constructor(
    private vacaturePassService: VacaturePassService,
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

    this.vacature = this.vacaturePassService.getVacature();
  }

  goBack(): void {
    this.location.back();
  }

  generatePdf() {
    console.log("hello")
    if (this.parentFormVacature) {
      this.pdfGenerator.generatePdf(this.parentFormVacature)
    }
  }

  ngOnDestroy(): void {
    this.subscription.unsubscribe()
  }

}

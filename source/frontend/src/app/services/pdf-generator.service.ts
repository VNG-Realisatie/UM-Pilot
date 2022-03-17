import {ElementRef, Injectable} from '@angular/core';
import {jsPDF} from "jspdf";
import {BehaviorSubject} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class PdfGeneratorService {

  loadingStatus = new BehaviorSubject<boolean>(false);
  loadingStatus$ = this.loadingStatus.asObservable();


  constructor() {
  }

  generatePdf(data: ElementRef) {
    const DATA = data.nativeElement;
    this.loadingStatus.next(true);
    const doc: jsPDF = new jsPDF("p", "mm", "a4");
    doc.html(DATA, {
      callback: (doc) => {
        doc.save();
        this.loadingStatus.next(false);
      },
      margin: 10, width: doc.internal.pageSize.getWidth(), windowWidth: 1500, autoPaging: 'text'
    });


  }
}

import {Component, Input, OnInit} from '@angular/core';
import {Arbeidsmarktkwalificatie} from "../../../entities/werkzoekende/arbeidsmarktkwalificatie";
import {Cursus} from "../../../entities/werkzoekende/cursus";

@Component({
  selector: 'app-cursus-werkzoekende-detailed',
  templateUrl: './cursus-werkzoekende-detailed.component.html',
  styleUrls: ['./cursus-werkzoekende-detailed.component.css']
})
export class CursusWerkzoekendeDetailedComponent implements OnInit {

  @Input()
  data?: Arbeidsmarktkwalificatie;

  cursussen?: Cursus[];

  constructor() {
  }

  ngOnInit(): void {
    this.cursussen = this.data?.cursus;
  }

}

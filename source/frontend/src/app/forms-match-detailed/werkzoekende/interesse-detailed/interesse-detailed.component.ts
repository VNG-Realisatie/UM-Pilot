import {Component, Input, OnInit} from '@angular/core';
import {Arbeidsmarktkwalificatie} from "../../../entities/werkzoekende/arbeidsmarktkwalificatie";
import {Interesse} from "../../../entities/werkzoekende/interesse";

@Component({
  selector: 'app-interesse-detailed',
  templateUrl: './interesse-detailed.component.html',
  styleUrls: ['./interesse-detailed.component.css']
})
export class InteresseDetailedComponent implements OnInit {

  @Input()
  data?: Arbeidsmarktkwalificatie;

  interesses?: Interesse[];

  constructor() {
  }

  ngOnInit(): void {
    this.interesses = this.data?.interesse;
  }

}

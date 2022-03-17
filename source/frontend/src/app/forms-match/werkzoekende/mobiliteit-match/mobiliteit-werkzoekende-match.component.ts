import {Component, Input, OnInit} from '@angular/core';
import {MPWerkzoekendeMatchBemiddelaar} from "../../../entities/werkzoekende/mPWerkzoekendeMatchBemiddelaar";
import {Werkzoekende} from "../../../entities/werkzoekende/werkzoekende";

@Component({
  selector: 'app-mobiliteit-werkzoekende-match',
  templateUrl: './mobiliteit-werkzoekende-match.component.html',
  styleUrls: ['./mobiliteit-werkzoekende-match.component.css']
})
export class MobiliteitWerkzoekendeMatchComponent implements OnInit {

  @Input()
  data?: MPWerkzoekendeMatchBemiddelaar | Werkzoekende;

  bemiddelingspostcode?: string
  maximaleReisafstand?: number
  maximaleReistijd?: number

  constructor() {
  }

  ngOnInit(): void {
    this.bemiddelingspostcode = this.data?.mobiliteit?.bemiddelingspostcode;
    this.maximaleReisafstand = this.data?.mobiliteit?.maximaleReisafstand;
    this.maximaleReistijd = this.data?.mobiliteit?.maximaleReistijd
  }

}

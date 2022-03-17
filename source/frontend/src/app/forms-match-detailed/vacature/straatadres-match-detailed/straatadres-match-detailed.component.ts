import {Component, Input, OnInit} from '@angular/core';
import {Straatadres} from "../../../entities/vacature/straatadres";

@Component({
  selector: 'app-straatadres-match-detailed',
  templateUrl: './straatadres-match-detailed.component.html',
  styleUrls: ['./straatadres-match-detailed.component.css']
})
export class StraatadresMatchDetailedComponent implements OnInit {

  @Input()
  data?: Straatadres;

  aanduidingBijHuisnummer?: string;
  huisletter?: string;
  huisnummer?: number;
  huisnummertoevoeging?: string;
  naamOpenbareRuimte?: string;
  straatnaam?: string;
  woonbootverwijzing?: string;
  woonwagenverwijzing?: string;

  constructor() {
  }

  ngOnInit(): void {
    this.aanduidingBijHuisnummer = this.data?.straatadres?.aanduidingBijHuisnummer;
    this.huisletter = this.data?.straatadres?.huisletter;
    this.huisnummer = this.data?.straatadres?.huisnummer;
    this.huisnummertoevoeging = this.data?.straatadres?.huisnummertoevoeging;
    this.naamOpenbareRuimte = this.data?.straatadres?.naamOpenbareRuimte;
    this.straatnaam = this.data?.straatadres?.straatnaam;
    this.woonbootverwijzing = this.data?.straatadres?.woonbootverwijzing;
    this.woonwagenverwijzing = this.data?.straatadres?.woonwagenverwijzing;

  }

}

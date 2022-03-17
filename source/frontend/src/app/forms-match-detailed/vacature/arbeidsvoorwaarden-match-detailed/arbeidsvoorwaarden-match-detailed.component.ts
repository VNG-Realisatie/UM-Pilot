import {Component, Input, OnInit} from '@angular/core';
import {dateFormatterString} from "../../../util/dateformatter";
import {Vacature} from "../../../entities/vacature/vacature";

@Component({
  selector: 'app-arbeidsvoorwaarden-match-detailed',
  templateUrl: './arbeidsvoorwaarden-match-detailed.component.html',
  styleUrls: ['./arbeidsvoorwaarden-match-detailed.component.css']
})
export class ArbeidsvoorwaardenMatchDetailedComponent implements OnInit {
  @Input()
  data?: Vacature;

  datumAanvangWerkzaamheden?: string
  datumEindeWerkzaamheden?: string
  omschrijvingArbeidsvoorwaarden?: string
  salarisIndicatie?: string


  constructor() {
  }

  ngOnInit(): void {
    this.datumAanvangWerkzaamheden = dateFormatterString(this.data?.arbeidsVoorwaarden?.datumAanvangWerkzaamheden);
    this.datumEindeWerkzaamheden = dateFormatterString(this.data?.arbeidsVoorwaarden?.datumEindeWerkzaamheden);
    this.omschrijvingArbeidsvoorwaarden = this.data?.arbeidsVoorwaarden?.omschrijvingArbeidsvoorwaarden;
    this.salarisIndicatie = this.data?.arbeidsVoorwaarden?.salarisIndicatie;
  }

}

import {Component, Input, OnInit} from '@angular/core';
import {MPVacatureMatch} from "../../../entities/vacature/mPVacatureMatch";
import {dateFormatterString} from "../../../util/dateformatter";
import {Vacature} from "../../../entities/vacature/vacature";

@Component({
  selector: 'app-arbeidsvoorwaarden-match',
  templateUrl: './arbeidsvoorwaarden-match.component.html',
  styleUrls: ['./arbeidsvoorwaarden-match.component.css']
})
export class ArbeidsvoorwaardenMatchComponent implements OnInit {

  @Input()
  data?: MPVacatureMatch | Vacature;

  datumAanvangWerkzaamheden?: string
  datumEindeWerkzaamheden?: string
  salarisIndicatie?: string

  constructor() {
  }

  ngOnInit(): void {

    this.datumAanvangWerkzaamheden = dateFormatterString(this.data?.arbeidsVoorwaarden?.datumAanvangWerkzaamheden);
    this.datumEindeWerkzaamheden = dateFormatterString(this.data?.arbeidsVoorwaarden?.datumEindeWerkzaamheden);
    this.salarisIndicatie = this.data?.arbeidsVoorwaarden?.salarisIndicatie;

  }

}

import {Component, Input, OnInit} from '@angular/core';
import {MPVacatureMatch} from "../../../entities/vacature/mPVacatureMatch";
import {Beroepsnaam, determineBeroepsnaamType} from "../../../entities/shared/beroepsnaam";
import {Vacature} from "../../../entities/vacature/vacature";

@Component({
  selector: 'app-beroep-match',
  templateUrl: './beroep-match.component.html',
  styleUrls: ['./beroep-match.component.css']
})
export class BeroepMatchComponent implements OnInit {

  @Input()
  data?: MPVacatureMatch | Vacature;

  beroepsnaam?: Beroepsnaam

  codedOrNot?: boolean;

  constructor() {
  }

  ngOnInit(): void {
    this.codedOrNot = determineBeroepsnaamType(this.data?.beroep)
    this.beroepsnaam = this.data?.beroep
  }


}

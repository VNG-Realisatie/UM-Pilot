import {Component, Input, OnInit} from '@angular/core';
import {MPVacatureMatch} from "../../../entities/vacature/mPVacatureMatch";
import {Vacature} from "../../../entities/vacature/vacature";
import {MPArbeidsmarktkwalificatie} from "../../../entities/werkzoekende/mPArbeidsmarktkwalificatie";
import {Rijbewijs} from 'src/app/entities/shared/rijbewijs';

@Component({
  selector: 'app-rijbewijs-match',
  templateUrl: './rijbewijs-match.component.html',
  styleUrls: ['./rijbewijs-match.component.css']
})
export class RijbewijsMatchComponent implements OnInit {

  @Input()
  data?: MPVacatureMatch | Vacature | MPArbeidsmarktkwalificatie;

  rijbewijzen?: Rijbewijs[];

  constructor() {
  }

  ngOnInit(): void {
    this.rijbewijzen = this.data?.rijbewijs;

  }
}

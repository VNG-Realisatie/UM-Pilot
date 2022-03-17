import {Component, Input, OnInit} from '@angular/core';
import {MPWerkzoekendeMatchBemiddelaar} from "../../../entities/werkzoekende/mPWerkzoekendeMatchBemiddelaar";
import {Beroepsnaam, determineBeroepsnaamType} from "../../../entities/shared/beroepsnaam";
import {BeroepsnaamOngecodeerd} from "../../../entities/shared/beroepsnaamOngecodeerd";
import {BeroepsnaamGecodeerd} from "../../../entities/shared/beroepsnaamGecodeerd";

@Component({
  selector: 'app-bemiddelingsberoep-match',
  templateUrl: './bemiddelingsberoep-match.component.html',
  styleUrls: ['./bemiddelingsberoep-match.component.css']
})
export class BemiddelingsberoepMatchComponent implements OnInit {

  @Input()
  data?: MPWerkzoekendeMatchBemiddelaar

  beroepen?: {
    beroepsnaam: BeroepsnaamGecodeerd | BeroepsnaamOngecodeerd;
    type: boolean
  }[]

  constructor() {
  }

  ngOnInit(): void {
    this.beroepen = this.data?.bemiddelingsberoep?.map(x => {
      return {beroepsnaam: x, type: this.checkBeroepsnaam(x)}
    })
  }

  checkBeroepsnaam(beroepsnaam: Beroepsnaam | undefined): boolean {
    return determineBeroepsnaamType(beroepsnaam)
  }

}

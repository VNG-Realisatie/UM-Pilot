import {Component, Input, OnInit} from '@angular/core';
import {MPArbeidsmarktkwalificatie} from "../../../entities/werkzoekende/mPArbeidsmarktkwalificatie";
import {Beroepsnaam, determineBeroepsnaamType} from "../../../entities/shared/beroepsnaam";

@Component({
  selector: 'app-werkervaring-werkzoekende-match',
  templateUrl: './werkervaring-werkzoekende-match.component.html',
  styleUrls: ['./werkervaring-werkzoekende-match.component.css']
})
export class WerkervaringWerkzoekendeMatchComponent implements OnInit {
  @Input()
  data?: MPArbeidsmarktkwalificatie

  werkervaringen?: {
    datumAanvangWerkzaamheden?: string;
    datumEindeWerkzaamheden?: string;
    naamOrganisatie?: string;
    beroep?: Beroepsnaam;
    type: boolean;
  }[];

  constructor() {
  }

  ngOnInit(): void {
    this.werkervaringen = this.data?.werkervaring?.map(x => {
      return {
        datumAanvangWerkzaamheden: x.datumAanvangWerkzaamheden,
        datumEindeWerkzaamheden: x.datumEindeWerkzaamheden,
        naamOrganisatie: x.naamOrganisatie,
        beroep: x.beroep,
        type: this.checkBeroepsnaam(x.beroep)
      }
    })
  }


  checkBeroepsnaam(beroepsnaam: Beroepsnaam | undefined): boolean {
    return determineBeroepsnaamType(beroepsnaam)
  }

}

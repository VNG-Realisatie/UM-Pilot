import {Component, Input, OnInit} from '@angular/core';
import {Beroepsnaam, determineBeroepsnaamType} from "../../../entities/shared/beroepsnaam";
import {Arbeidsmarktkwalificatie} from "../../../entities/werkzoekende/arbeidsmarktkwalificatie";

@Component({
  selector: 'app-werkervaring-werkzoekende-detailed',
  templateUrl: './werkervaring-werkzoekende-detailed.component.html',
  styleUrls: ['./werkervaring-werkzoekende-detailed.component.css']
})
export class WerkervaringWerkzoekendeDetailedComponent implements OnInit {

  @Input()
  data?: Arbeidsmarktkwalificatie

  werkervaringen?: {
    datumAanvangWerkzaamheden?: string;
    datumEindeWerkzaamheden?: string;
    naamOrganisatie?: string;
    beroep?: Beroepsnaam;
    type?: boolean;
    toelichtingWerkervaring?: string;
  }[]

  constructor() {
  }

  ngOnInit(): void {
    this.werkervaringen = this.data?.werkervaring?.map(x => {
      return {
        datumAanvangWerkzaamheden: x.datumAanvangWerkzaamheden,
        datumEindeWerkzaamheden: x.datumEindeWerkzaamheden,
        naamOrganisatie: x.naamOrganisatie,
        beroep: x.beroep,
        type: this.checkBeroepsnaam(x.beroep),
        toelichtingWerkervaring: x.toelichtingWerkervaring
      }
    })
  }


  checkBeroepsnaam(beroepsnaam: Beroepsnaam | undefined): boolean {
    return determineBeroepsnaamType(beroepsnaam)
  }


}

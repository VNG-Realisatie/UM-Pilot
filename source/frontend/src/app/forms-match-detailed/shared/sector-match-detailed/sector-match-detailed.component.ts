import {Component, Input, OnInit} from '@angular/core';
import {MPWerkzoekendeMatchBemiddelaar} from "../../../entities/werkzoekende/mPWerkzoekendeMatchBemiddelaar";
import {SectorBeroepsEnBedrijfsleven} from "../../../entities/shared/sectorBeroepsEnBedrijfsleven";
import {Werkgever} from "../../../entities/vacature/werkgever";

@Component({
  selector: 'app-sector-match-detailed',
  templateUrl: './sector-match-detailed.component.html',
  styleUrls: ['./sector-match-detailed.component.css']
})
export class SectorMatchDetailedComponent implements OnInit {

  @Input()
  data?: MPWerkzoekendeMatchBemiddelaar | Werkgever;

  sectoren?: SectorBeroepsEnBedrijfsleven[];

  constructor() {
  }

  ngOnInit(): void {
    this.sectoren = this.data?.sector;
  }

}

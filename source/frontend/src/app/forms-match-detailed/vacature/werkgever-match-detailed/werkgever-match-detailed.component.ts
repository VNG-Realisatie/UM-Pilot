import {Component, Input, OnInit} from '@angular/core';
import {Vacature} from "../../../entities/vacature/vacature";
import {Werkgever} from "../../../entities/vacature/werkgever";
import {SectorBeroepsEnBedrijfsleven} from 'src/app/entities/shared/sectorBeroepsEnBedrijfsleven';

@Component({
  selector: 'app-werkgever-match-detailed',
  templateUrl: './werkgever-match-detailed.component.html',
  styleUrls: ['./werkgever-match-detailed.component.css']
})
export class WerkgeverMatchDetailedComponent implements OnInit {

  @Input()
  data?: Vacature;

  werkgever?: Werkgever;
  sectoren?: SectorBeroepsEnBedrijfsleven[];
  handelsnaamOrganisatie?: string;


  constructor() {
  }

  ngOnInit(): void {
    this.handelsnaamOrganisatie = this.data?.werkgever?.handelsnaamOrganisatie
    this.werkgever = this.data?.werkgever;
    this.sectoren = this.werkgever?.sector
  }

}

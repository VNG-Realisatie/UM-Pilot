import {Component, Input, OnInit} from '@angular/core';
import {MPVacatureMatch} from "../../../entities/vacature/mPVacatureMatch";
import {Vacature} from "../../../entities/vacature/vacature";

@Component({
  selector: 'app-sector-match',
  templateUrl: './sector-match.component.html',
  styleUrls: ['./sector-match.component.css']
})
export class SectorMatchComponent implements OnInit {

  @Input()
  data?: MPVacatureMatch | Vacature;

  codeSbi?: number


  constructor() {
  }

  ngOnInit(): void {
    this.codeSbi = this.data?.sector?.codeSbi;
  }

}

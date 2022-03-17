import {Component, Input, OnInit} from '@angular/core';
import {MPVacatureMatch} from "../../../entities/vacature/mPVacatureMatch";
import {Vacature} from "../../../entities/vacature/vacature";
import {Cursus} from "../../../entities/vacature/cursus";

@Component({
  selector: 'app-cursus-match',
  templateUrl: './cursus-match.component.html',
  styleUrls: ['./cursus-match.component.css']
})
export class CursusMatchComponent implements OnInit {

  @Input()
  data?: MPVacatureMatch | Vacature;

  cursussen?: Cursus[]

  constructor() {
  }

  ngOnInit(): void {
    this.cursussen = this.data?.cursus;
  }

}

import {Component, Input, OnInit} from '@angular/core';
import {MPVacatureMatch} from "../../../entities/vacature/mPVacatureMatch";
import {Vacature} from "../../../entities/vacature/vacature";
import {Werkervaring} from 'src/app/entities/vacature/werkervaring';

@Component({
  selector: 'app-werkervaring-match',
  templateUrl: './werkervaring-match.component.html',
  styleUrls: ['./werkervaring-match.component.css']
})
export class WerkervaringMatchComponent implements OnInit {

  @Input()
  data?: MPVacatureMatch | Vacature;

  werkervaringen?: Werkervaring[];


  constructor() {
  }

  ngOnInit(): void {
    this.werkervaringen = this.data?.werkervaring;
  }


}


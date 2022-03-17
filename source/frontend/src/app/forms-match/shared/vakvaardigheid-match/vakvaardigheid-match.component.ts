import {Component, Input, OnInit} from '@angular/core';
import {MPVacatureMatch} from "../../../entities/vacature/mPVacatureMatch";
import {Vacature} from "../../../entities/vacature/vacature";
import {MPArbeidsmarktkwalificatie} from "../../../entities/werkzoekende/mPArbeidsmarktkwalificatie";
import {Vakvaardigheid} from "../../../entities/shared/vakvaardigheid";

@Component({
  selector: 'app-vakvaardigheid-match',
  templateUrl: './vakvaardigheid-match.component.html',
  styleUrls: ['./vakvaardigheid-match.component.css']
})
export class VakvaardigheidMatchComponent implements OnInit {

  @Input()
  data?: MPVacatureMatch | Vacature | MPArbeidsmarktkwalificatie;

  vakvaardigheiden?: Vakvaardigheid[];


  constructor() {
  }

  ngOnInit(): void {
    this.vakvaardigheiden = this.data?.vakvaardigheid
  }


}

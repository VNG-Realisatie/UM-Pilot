import {Component, Input, OnInit} from '@angular/core';
import {StraatadresBuitenland} from "../../../entities/vacature/straatadresBuitenland";

@Component({
  selector: 'app-straatadres-buitenland-match-detailed',
  templateUrl: './straatadres-buitenland-match-detailed.component.html',
  styleUrls: ['./straatadres-buitenland-match-detailed.component.css']
})
export class StraatadresBuitenlandMatchDetailedComponent implements OnInit {

  @Input()
  data?: StraatadresBuitenland;

  huisnummerBuitenland?: string;
  straatnaamBuitenland?: string;


  constructor() {
  }

  ngOnInit(): void {

    this.huisnummerBuitenland = this.data?.straatadresbuitenland?.huisnummerBuitenland;
    this.straatnaamBuitenland = this.data?.straatadresbuitenland?.straatnaamBuitenland;

  }

}

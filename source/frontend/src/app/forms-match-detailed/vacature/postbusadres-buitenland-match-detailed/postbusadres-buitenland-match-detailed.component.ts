import {Component, Input, OnInit} from '@angular/core';
import {PostbusadresBuitenland} from "../../../entities/vacature/postbusadresBuitenland";

@Component({
  selector: 'app-postbusadres-buitenland-match-detailed',
  templateUrl: './postbusadres-buitenland-match-detailed.component.html',
  styleUrls: ['./postbusadres-buitenland-match-detailed.component.css']
})
export class PostbusadresBuitenlandMatchDetailedComponent implements OnInit {

  @Input()
  data?: PostbusadresBuitenland;

  postbusnummerBuitenland?: string;

  constructor() {
  }

  ngOnInit(): void {
    this.postbusnummerBuitenland = this.data?.postbusadresbuitenland?.postbusnummerBuitenland
  }


}

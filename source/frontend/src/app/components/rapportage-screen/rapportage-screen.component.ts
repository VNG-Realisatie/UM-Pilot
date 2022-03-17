import { Component, OnInit } from '@angular/core';
import {environment} from "../../../environments/environment";

@Component({
  selector: 'app-rapportage-screen',
  templateUrl: './rapportage-screen.component.html',
  styleUrls: ['./rapportage-screen.component.css']
})
export class RapportageScreenComponent implements OnInit {

  kibanaUrl? : string;

  constructor() { }

  ngOnInit(): void {
    this.kibanaUrl = `${environment.kibanaUrl}`
  }

}

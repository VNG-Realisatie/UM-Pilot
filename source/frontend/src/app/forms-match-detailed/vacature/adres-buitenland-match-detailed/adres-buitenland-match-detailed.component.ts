import {Component, Input, OnInit} from '@angular/core';
import {FormBuilder} from "@angular/forms";
import {AdresBuitenland} from "../../../entities/vacature/adresBuitenland";
import {isStraatadresBuitenland, StraatadresBuitenland} from "../../../entities/vacature/straatadresBuitenland";
import {PostbusadresBuitenland} from "../../../entities/vacature/postbusadresBuitenland";

@Component({
  selector: 'app-adres-buitenland-match-detailed',
  templateUrl: './adres-buitenland-match-detailed.component.html',
  styleUrls: ['./adres-buitenland-match-detailed.component.css']
})
export class AdresBuitenlandMatchDetailedComponent implements OnInit {

  @Input()
  data?: AdresBuitenland;


  landencodeIso?: string;
  landsnaam?: string;
  locatieomschrijvingBuitenland?: string;
  postcodeBuitenland?: string;
  regionaamBuitenland?: string;
  woonplaatsnaamBuitenland?: string;


  isStraatadresBuitenlandVar?: boolean

  constructor(private fb: FormBuilder) {
  }

  ngOnInit(): void {
    this.isStraatadresBuitenland();


    this.landencodeIso = this.data?.adresBuitenland?.landencodeIso;
    this.landsnaam = this.data?.adresBuitenland?.landsnaam;
    this.locatieomschrijvingBuitenland = this.data?.adresBuitenland?.locatieomschrijvingBuitenland;
    this.postcodeBuitenland = this.data?.adresBuitenland?.postcodeBuitenland;
    this.regionaamBuitenland = this.data?.adresBuitenland?.regionaamBuitenland;
    this.woonplaatsnaamBuitenland = this.data?.adresBuitenland?.woonplaatsnaamBuitenland;


  }

  isStraatadresBuitenland(): void {
    this.isStraatadresBuitenlandVar = isStraatadresBuitenland(this.data?.adresBuitenland?.adresDetailsBuitenland);
  }

  getStraatadresBuitenland() {
    return this.data?.adresBuitenland?.adresDetailsBuitenland as StraatadresBuitenland;
  }

  getPostbusadresBuitenland() {
    return this.data?.adresBuitenland?.adresDetailsBuitenland as PostbusadresBuitenland;

  }
}

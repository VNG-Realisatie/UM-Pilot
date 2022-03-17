import {Component, Input, OnInit} from '@angular/core';
import {AdresNederland} from "../../../entities/vacature/adresNederland";
import {isStraatadres, Straatadres} from "../../../entities/vacature/straatadres";
import {isPostbusadres, Postbusadres} from "../../../entities/vacature/postbusadres";
import {AntwoordnummerAdres} from "../../../entities/vacature/antwoordnummerAdres";

@Component({
  selector: 'app-adres-nederland-match-detailed',
  templateUrl: './adres-nederland-match-detailed.component.html',
  styleUrls: ['./adres-nederland-match-detailed.component.css']
})
export class AdresNederlandMatchDetailedComponent implements OnInit {

  @Input()
  data?: AdresNederland;

  codeGemeente?: string;
  district?: string;
  gemeentedeel?: string;
  gemeentenaam?: string;
  identificatiecodeNummeraanduiding?: string;
  identificatiecodeVerblijfplaats?: string;
  locatieomschrijving?: string;
  postcode?: string;
  woonplaatsnaam?: string;

  typeAdres?: number;

  constructor() {

  }

  ngOnInit(): void {
    this.typeAdres = this.determineTypeAdres();


    this.codeGemeente = this.data?.adresNederland?.codeGemeente;
    this.district = this.data?.adresNederland?.district;
    this.gemeentedeel = this.data?.adresNederland?.gemeentedeel;
    this.gemeentenaam = this.data?.adresNederland?.gemeentenaam;
    this.identificatiecodeNummeraanduiding = this.data?.adresNederland?.identificatiecodeNummeraanduiding;
    this.identificatiecodeVerblijfplaats = this.data?.adresNederland?.identificatiecodeVerblijfplaats;
    this.locatieomschrijving = this.data?.adresNederland?.locatieomschrijving;
    this.postcode = this.data?.adresNederland?.postcode;
    this.woonplaatsnaam = this.data?.adresNederland?.woonplaatsnaam;


  }


  determineTypeAdres() {
    const adresDetails = this.data?.adresNederland?.adresDetails
    if (isStraatadres(adresDetails)) {
      return 0;
    } else if (isPostbusadres(adresDetails)) {
      return 1;
    } else {
      return 2;
    }
  }


  getStraatadres() {
    return this.data?.adresNederland?.adresDetails as Straatadres;
  }

  getPostbusadres() {
    return this.data?.adresNederland?.adresDetails as Postbusadres;
  }

  getAntwoordnummeradres() {
    return this.data?.adresNederland?.adresDetails as AntwoordnummerAdres;
  }


}

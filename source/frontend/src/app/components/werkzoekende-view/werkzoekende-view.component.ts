import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {Location} from "@angular/common";
import {WerkzoekendePassService} from "../../services/werkzoekende-pass.service";
import {Werkzoekende} from "../../entities/werkzoekende/werkzoekende";

@Component({
  selector: 'app-werkzoekende-view',
  templateUrl: './werkzoekende-view.component.html',
  styleUrls: ['./werkzoekende-view.component.css']
})
export class WerkzoekendeViewComponent implements OnInit {

  werkzoekende?: Werkzoekende;

  constructor(private route: ActivatedRoute,
              private location: Location,
              private werkzoekendePassService: WerkzoekendePassService) {
  }

  ngOnInit(): void {
    this.werkzoekende = this.werkzoekendePassService.getWerkzoekende();
  }

  goBack(): void {
    this.location.back();
  }

}

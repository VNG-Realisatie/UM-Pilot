import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {Location} from "@angular/common";
import {VacaturePassService} from "../../services/vacature-pass.service";
import {Vacature} from "../../entities/vacature/vacature";

@Component({
  selector: 'app-vacature-view',
  templateUrl: './vacature-view.component.html',
  styleUrls: ['./vacature-view.component.css']
})
export class VacatureViewComponent implements OnInit {

  vacature?: Vacature;

  constructor(private route: ActivatedRoute,
              private location: Location,
              private vacaturePassService: VacaturePassService) {
  }

  ngOnInit(): void {
    this.vacature = this.vacaturePassService.getVacature();
  }

  goBack(): void {
    this.location.back();
  }

}

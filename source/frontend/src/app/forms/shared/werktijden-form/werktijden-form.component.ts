import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {AbstractBaseFormComponent} from "../../abstract-base-form/abstract-base-form.component";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {indicatieThreeValues} from "../../../forms-util/formcontrol-value-enums";
import {Subscription} from "rxjs";
import {MPVacature} from "../../../entities/vacature/mPVacature";
import {MPWerkzoekende} from "../../../entities/werkzoekende/mPWerkzoekende";


@Component({
  selector: 'app-werktijden-form',
  templateUrl: './werktijden-form.component.html',
  styleUrls: ['./werktijden-form.component.css']
})
export class WerktijdenFormComponent extends AbstractBaseFormComponent implements OnInit {

  @Output()
  formReady = new EventEmitter<FormGroup>();

  @Output()
  valueChangeVacature = new EventEmitter<Partial<MPVacature>>();

  @Output()
  valueChangeWerkzoekende = new EventEmitter<Partial<MPWerkzoekende>>();

  form: FormGroup = this.fb.group({});

  private subscription = new Subscription();

  indicatieThreeValues = indicatieThreeValues;


  constructor(private fb: FormBuilder) {
    super();
  }

  ngOnInit(): void {
    this.form = this.fb.group({
      aantalWerkurenPerWeekMinimaal: ['', [Validators.min(0), Validators.max(99)]],
      aantalWerkurenPerWeekMaximaal: ['', [Validators.min(0), Validators.max(99)]],
      indicatieKantoortijden: ['', []],

    });

    this.subscription.add(
      this.form.valueChanges.subscribe((value) => {
        const entity = {
          werktijden: {
            aantalWerkurenPerWeekMinimaal: value.aantalWerkurenPerWeekMinimaal,
            aantalWerkurenPerWeekMaximaal: value.aantalWerkurenPerWeekMaximaal,
            indicatieKantoortijden: value.indicatieKantoortijden
          }
        }
        this.valueChangeVacature.emit(entity);
        this.valueChangeWerkzoekende.emit(entity);
      })
    );

    this.formReady.emit(this.form);
  }


  ngOnDestroy() {
    this.subscription.unsubscribe();
  }

}

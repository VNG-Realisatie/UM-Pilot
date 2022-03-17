import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {FormArray, FormBuilder, FormGroup, Validators} from "@angular/forms";
import {AbstractBaseFormComponent} from "../../abstract-base-form/abstract-base-form.component";
import {indicatieThreeValues} from "../../../forms-util/formcontrol-value-enums";
import {Subscription} from "rxjs";
import {MPVacature} from "../../../entities/vacature/mPVacature";
import {MPWerkzoekende} from "../../../entities/werkzoekende/mPWerkzoekende";

@Component({
  selector: 'app-vervoermiddel-form',
  templateUrl: './vervoermiddel-form.component.html',
  styleUrls: ['./vervoermiddel-form.component.css']
})
export class VervoermiddelFormComponent extends AbstractBaseFormComponent implements OnInit {

  @Output()
  formReady = new EventEmitter<FormArray>();

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
      vervoermiddel: this.fb.array([]),
    });

    this.subscription.add(
      this.form.controls.vervoermiddel.valueChanges.subscribe((value) => {
        this.valueChangeVacature.emit({
          vervoermiddel: value
        });

        this.valueChangeWerkzoekende.emit({
          vervoermiddel: value
        })

      })
    );


    this.formReady.emit(this.t);
  }

  addVervoermiddel() {
    this.t.push(this.fb.group({
      indicatieBeschikbaarVoorUitvoeringWerk: ['', [Validators.required]],
      indicatieBeschikbaarVoorWoonWerkverkeer: ['', [Validators.required]]
    }));
  }

  removeVervoermiddel(i: number) {
    this.t.removeAt(i);
  }

  get t() {
    return this.f.vervoermiddel as FormArray;
  }

  ngOnDestroy() {
    this.subscription.unsubscribe();
  }

}

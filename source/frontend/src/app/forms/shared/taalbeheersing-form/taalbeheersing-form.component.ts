import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {AbstractBaseFormComponent} from "../../abstract-base-form/abstract-base-form.component";
import {FormArray, FormBuilder, FormGroup, Validators} from "@angular/forms";
import {taalBeheersingValues} from "../../../forms-util/formcontrol-value-enums";
import {Subscription} from "rxjs";
import {MPVacature} from "../../../entities/vacature/mPVacature";
import {MPArbeidsmarktkwalificatie} from "../../../entities/werkzoekende/mPArbeidsmarktkwalificatie";

@Component({
  selector: 'app-taalbeheersing-form',
  templateUrl: './taalbeheersing-form.component.html',
  styleUrls: ['./taalbeheersing-form.component.css']
})
export class TaalbeheersingFormComponent extends AbstractBaseFormComponent implements OnInit {

  @Output()
  formReady = new EventEmitter<FormArray>();

  @Output()
  valueChangeVacature = new EventEmitter<Partial<MPVacature>>();

  @Output()
  valueChangeWerkzoekende = new EventEmitter<Partial<MPArbeidsmarktkwalificatie>>();

  form: FormGroup = this.fb.group({});

  private subscription = new Subscription();

  taalBeheersingValues = taalBeheersingValues

  constructor(private fb: FormBuilder) {
    super();
  }

  ngOnInit(): void {
    this.form = this.fb.group({
      taalbeheersing: this.fb.array([])
    })

    this.subscription.add(
      this.form.controls.taalbeheersing.valueChanges.subscribe((value) => {
        const entity = {
          taalbeheersing: value
        };

        this.valueChangeVacature.emit(entity);
        this.valueChangeWerkzoekende.emit(entity);
      })
    );

    this.formReady.emit(this.t);
  }


  addTaalbeheersing() {
    this.t.push(this.fb.group({
      codeTaal: ['', [Validators.required]],
      codeNiveauTaalbeheersingMondeling: ['', []],
      codeNiveauTaalbeheersingSchriftelijk: ['', []],
      codeNiveauTaalbeheersingLezen: ['', []],
      codeNiveauTaalbeheersingLuisteren: ['', []],
    }));
  }

  removeTaalbeheersing(i: number) {
    this.t.removeAt(i);
  }


  get t() {
    return this.f.taalbeheersing as FormArray;
  }

  ngOnDestroy() {
    this.subscription.unsubscribe();
  }


}

import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {AbstractBaseFormComponent} from "../../abstract-base-form/abstract-base-form.component";
import {FormArray, FormBuilder, FormGroup, Validators} from "@angular/forms";
import {
  beheersingGedragscompetentieValues,
  codeGedragscompetentieValues
} from "../../../forms-util/formcontrol-value-enums";
import {MPVacature} from "../../../entities/vacature/mPVacature";
import {Subscription} from "rxjs";
import {MPArbeidsmarktkwalificatie} from "../../../entities/werkzoekende/mPArbeidsmarktkwalificatie";

@Component({
  selector: 'app-gedragscompetentie-form',
  templateUrl: './gedragscompetentie-form.component.html',
  styleUrls: ['./gedragscompetentie-form.component.css']
})
export class GedragscompetentieFormComponent extends AbstractBaseFormComponent implements OnInit {
  @Output()
  formReady = new EventEmitter<FormArray>();

  @Output()
  valueChangeVacature = new EventEmitter<Partial<MPVacature>>();

  @Output()
  valueChangeWerkzoekende = new EventEmitter<Partial<MPArbeidsmarktkwalificatie>>();

  form: FormGroup = this.fb.group({});

  private subscription = new Subscription();

  codeGedragscompetentieValues = codeGedragscompetentieValues;
  beheersingGedragscompetentieValues = beheersingGedragscompetentieValues;


  constructor(private fb: FormBuilder) {
    super();
  }

  ngOnInit(): void {
    this.form = this.fb.group({
      gedragscompetentie: this.fb.array([])
    })

    this.subscription.add(
      this.form.controls.gedragscompetentie.valueChanges.subscribe((value) => {
        const entity = {
          gedragscompetentie: value
        };


        this.valueChangeVacature.emit(entity);
        this.valueChangeWerkzoekende.emit(entity)
      })
    );

    this.formReady.emit(this.t);
  }

  addGedragscompetentie() {
    this.t.push(this.fb.group({
      codeGedragscompetentie: ['', []],
      omschrijvingGedragscompetentie: ['', [Validators.maxLength(120)]],
      codeBeheersingGedragscompetentie: ['', []]
    }));
  }

  removeGedragscompetentie(i: number) {
    this.t.removeAt(i);
  }

  get t() {
    return this.f.gedragscompetentie as FormArray;
  }

  ngOnDestroy() {
    this.subscription.unsubscribe();
  }


}

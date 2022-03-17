import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {FormArray, FormBuilder, FormGroup} from "@angular/forms";
import {Subscription} from "rxjs";
import {AbstractBaseFormComponent} from "../../abstract-base-form/abstract-base-form.component";
import {codeNiveauOpleidingValues, indicatieDiplomaValues} from "../../../forms-util/formcontrol-value-enums";
import {MPArbeidsmarktkwalificatie} from "../../../entities/werkzoekende/mPArbeidsmarktkwalificatie";
import {dateFormatterNgbDate} from "../../../util/dateformatter";
import {MPOpleiding} from "../../../entities/werkzoekende/mPOpleiding";


@Component({
  selector: 'app-opleiding-werkzoekende-form',
  templateUrl: './opleiding-werkzoekende-form.component.html',
  styleUrls: ['./opleiding-werkzoekende-form.component.css']
})
export class OpleidingWerkzoekendeFormComponent extends AbstractBaseFormComponent implements OnInit {

  @Output()
  formReady = new EventEmitter<FormArray>();

  @Output()
  valueChangeWerkzoekende = new EventEmitter<Partial<MPArbeidsmarktkwalificatie>>();

  codeNiveauOpleidingValues = codeNiveauOpleidingValues;
  indicatieDiplomaValues = indicatieDiplomaValues;

  form: FormGroup = this.fb.group({});

  private subscription = new Subscription();


  constructor(private fb: FormBuilder) {
    super();
  }

  ngOnInit(): void {
    this.form = this.fb.group({
      opleiding: this.fb.array([])
    })

    this.subscription.add(
      this.form.controls.opleiding.valueChanges.subscribe((value) => {

        const mappedValues = value.map((x: MPOpleiding) => {
          return {
            codeNiveauOpleiding: x.codeNiveauOpleiding,
            indicatieDiploma: x.indicatieDiploma,
            datumDiploma: dateFormatterNgbDate(x.datumDiploma),
            opleidingsnaam: x.opleidingsnaam
          }
        });

        this.valueChangeWerkzoekende.emit({
          opleiding: mappedValues
        });
      })
    );

    this.formReady.emit(this.t);
  }

  addOpleiding() {
    this.t.push(this.fb.group({
      codeNiveauOpleiding: ['', []],
      indicatieDiploma: ['', []],
      datumDiploma: [null, []],
      opleidingsnaam: this.fb.group({})
    }));
  }

  removeOpleiding(i: number) {
    this.t.removeAt(i);
  }

  get t() {
    return this.f.opleiding as FormArray;
  }

  updateOpleidingsnaam(opleidingControl: FormGroup, group: FormGroup) {
    opleidingControl.setControl('opleidingsnaam', group);
  }

  ngOnDestroy() {
    this.subscription.unsubscribe();
  }

}

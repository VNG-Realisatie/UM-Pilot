import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {FormArray, FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Subscription} from "rxjs";
import {AbstractBaseFormComponent} from "../../abstract-base-form/abstract-base-form.component";
import {MPCursus} from "../../../entities/werkzoekende/mPCursus";
import {dateFormatterNgbDate} from "../../../util/dateformatter";
import {MPArbeidsmarktkwalificatie} from "../../../entities/werkzoekende/mPArbeidsmarktkwalificatie";

@Component({
  selector: 'app-cursus-werkzoekende-form',
  templateUrl: './cursus-werkzoekende-form.component.html',
  styleUrls: ['./cursus-werkzoekende-form.component.css']
})
export class CursusWerkzoekendeFormComponent extends AbstractBaseFormComponent implements OnInit {

  @Output()
  formReady = new EventEmitter<FormArray>();

  @Output()
  valueChangeWerkzoekende = new EventEmitter<Partial<MPArbeidsmarktkwalificatie>>();

  form: FormGroup = this.fb.group({});

  private subscription = new Subscription();

  constructor(private fb: FormBuilder) {
    super();
  }

  ngOnInit(): void {
    this.form = this.fb.group({
      cursus: this.fb.array([])
    })

    this.subscription.add(
      this.form.controls.cursus.valueChanges.subscribe((value) => {

        const mappedValues = value.map((x: MPCursus) => {
          return {naamCursus: x.naamCursus, datumCertificaat: dateFormatterNgbDate(x.datumCertificaat)}
        });
        this.valueChangeWerkzoekende.emit({
          cursus: mappedValues,
        });
      })
    );

    this.formReady.emit(this.t);
  }

  addCursus() {
    this.t.push(this.fb.group({
      naamCursus: ['', [Validators.required, Validators.maxLength(120)]],
      datumCertificaat: [null, []]
    }));
  }

  removeCursus(i: number) {
    this.t.removeAt(i);
  }

  get t() {
    return this.f.cursus as FormArray;
  }

  ngOnDestroy() {
    this.subscription.unsubscribe();
  }

}

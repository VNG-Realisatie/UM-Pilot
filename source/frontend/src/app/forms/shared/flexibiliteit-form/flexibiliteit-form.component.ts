import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {FormBuilder, FormGroup} from "@angular/forms";
import {AbstractBaseFormComponent} from "../../abstract-base-form/abstract-base-form.component";
import {codeRegioStraalValues, indicatieThreeValues} from "../../../forms-util/formcontrol-value-enums";
import {Subscription} from "rxjs";
import {dateFormatterNgbDate} from "../../../util/dateformatter";
import {MPVacature} from "../../../entities/vacature/mPVacature";
import {MPWerkzoekende} from "../../../entities/werkzoekende/mPWerkzoekende";

@Component({
  selector: 'app-flexibiliteit-form',
  templateUrl: './flexibiliteit-form.component.html',
  styleUrls: ['./flexibiliteit-form.component.css']
})
export class FlexibiliteitFormComponent extends AbstractBaseFormComponent implements OnInit {

  @Output()
  formReady = new EventEmitter<FormGroup>();

  @Output()
  valueChangeVacature = new EventEmitter<Partial<MPVacature>>();

  @Output()
  valueChangeWerkzoekende = new EventEmitter<Partial<MPWerkzoekende>>();

  form: FormGroup = this.fb.group({});

  private subscription = new Subscription();

  codeRegioStraalValues = codeRegioStraalValues;
  indicatieThreeValues = indicatieThreeValues;


  constructor(private fb: FormBuilder) {
    super();
  }

  ngOnInit(): void {
    this.form = this.fb.group({
      codeRegiostraal: ['', []],
      indicatieOnregelmatigWerkOfPloegendienst: ['', []],
      datumAanvangBeschikbaarVoorWerk: [null, []],
      datumEindeBeschikbaarVoorWerk: [null, []],
    });

    this.subscription.add(
      this.form.valueChanges.subscribe((value) => {

        const entity = {
          flexibiliteit: {
            codeRegiostraal: value.codeRegiostraal,
            indicatieOnregelmatigWerkOfPloegendienst: value.indicatieOnregelmatigWerkOfPloegendienst,
            datumAanvangBeschikbaarVoorWerk: dateFormatterNgbDate(value.datumAanvangBeschikbaarVoorWerk),
            datumEindeBeschikbaarVoorWerk: dateFormatterNgbDate(value.datumEindeBeschikbaarVoorWerk)
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

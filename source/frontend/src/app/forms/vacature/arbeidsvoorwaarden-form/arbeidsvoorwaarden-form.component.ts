import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {AbstractBaseFormComponent} from "../../abstract-base-form/abstract-base-form.component";
import {Subscription} from "rxjs";
import {dateFormatterNgbDate} from "../../../util/dateformatter";
import {MPVacature} from "../../../entities/vacature/mPVacature";

@Component({
  selector: 'app-arbeidsvoorwaarden-form',
  templateUrl: './arbeidsvoorwaarden-form.component.html',
  styleUrls: ['./arbeidsvoorwaarden-form.component.css']
})
export class ArbeidsvoorwaardenFormComponent extends AbstractBaseFormComponent implements OnInit {

  @Output()
  formReady = new EventEmitter<FormGroup>();

  @Output()
  valueChangeVacature = new EventEmitter<Partial<MPVacature>>();

  form: FormGroup = this.fb.group({});

  private subscription = new Subscription();

  constructor(private fb: FormBuilder) {
    super();
  }

  ngOnInit(): void {

    this.form = this.fb.group({
      datumAanvangWerkzaamheden: [null, []],
      datumEindeWerkzaamheden: [null, []],
      salarisIndicatie: ['', [Validators.maxLength(100)]]
    });

    this.subscription.add(
      this.form.valueChanges.subscribe((value) => {
        this.valueChangeVacature.emit({
          arbeidsVoorwaarden: {
            datumAanvangWerkzaamheden: dateFormatterNgbDate(value.datumAanvangWerkzaamheden),
            datumEindeWerkzaamheden: dateFormatterNgbDate(value.datumEindeWerkzaamheden),
            salarisIndicatie: value.salarisIndicatie
          }
        });
      })
    );

    this.formReady.emit(this.form);
  }

  ngOnDestroy() {
    this.subscription.unsubscribe();
  }

}

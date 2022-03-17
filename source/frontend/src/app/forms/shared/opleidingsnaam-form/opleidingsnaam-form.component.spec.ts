import {ComponentFixture, TestBed} from '@angular/core/testing';

import {OpleidingsnaamFormComponent} from './opleidingsnaam-form.component';

describe('OpleidingsnaamFormComponent', () => {
  let component: OpleidingsnaamFormComponent;
  let fixture: ComponentFixture<OpleidingsnaamFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [OpleidingsnaamFormComponent]
    })
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(OpleidingsnaamFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

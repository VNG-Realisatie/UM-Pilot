import {ComponentFixture, TestBed} from '@angular/core/testing';

import {OpleidingsnaamOngecodeerdFormComponent} from './opleidingsnaam-ongecodeerd-form.component';

describe('OpleidingsnaamOngecodeerdFormComponent', () => {
  let component: OpleidingsnaamOngecodeerdFormComponent;
  let fixture: ComponentFixture<OpleidingsnaamOngecodeerdFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [OpleidingsnaamOngecodeerdFormComponent]
    })
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(OpleidingsnaamOngecodeerdFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

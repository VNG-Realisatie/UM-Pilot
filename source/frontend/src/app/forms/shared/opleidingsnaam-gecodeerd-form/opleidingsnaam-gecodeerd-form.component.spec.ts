import {ComponentFixture, TestBed} from '@angular/core/testing';

import {OpleidingsnaamGecodeerdFormComponent} from './opleidingsnaam-gecodeerd-form.component';

describe('OpleidingsnaamGecodeerdFormComponent', () => {
  let component: OpleidingsnaamGecodeerdFormComponent;
  let fixture: ComponentFixture<OpleidingsnaamGecodeerdFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [OpleidingsnaamGecodeerdFormComponent]
    })
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(OpleidingsnaamGecodeerdFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

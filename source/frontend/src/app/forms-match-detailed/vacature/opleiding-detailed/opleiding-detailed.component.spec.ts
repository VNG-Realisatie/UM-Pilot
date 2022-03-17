import {ComponentFixture, TestBed} from '@angular/core/testing';

import {OpleidingDetailedComponent} from './opleiding-detailed.component';

describe('OpleidingDetailedComponent', () => {
  let component: OpleidingDetailedComponent;
  let fixture: ComponentFixture<OpleidingDetailedComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [OpleidingDetailedComponent]
    })
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(OpleidingDetailedComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

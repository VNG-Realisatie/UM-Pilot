import {ComponentFixture, TestBed} from '@angular/core/testing';

import {AdresNederlandMatchDetailedComponent} from './adres-nederland-match-detailed.component';

describe('AdresNederlandMatchDetailedComponent', () => {
  let component: AdresNederlandMatchDetailedComponent;
  let fixture: ComponentFixture<AdresNederlandMatchDetailedComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [AdresNederlandMatchDetailedComponent]
    })
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AdresNederlandMatchDetailedComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

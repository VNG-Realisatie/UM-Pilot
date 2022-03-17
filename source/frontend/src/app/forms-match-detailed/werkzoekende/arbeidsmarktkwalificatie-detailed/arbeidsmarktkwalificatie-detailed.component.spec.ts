import {ComponentFixture, TestBed} from '@angular/core/testing';

import {ArbeidsmarktkwalificatieDetailedComponent} from './arbeidsmarktkwalificatie-detailed.component';

describe('ArbeidsmarktkwalificatieDetailedComponent', () => {
  let component: ArbeidsmarktkwalificatieDetailedComponent;
  let fixture: ComponentFixture<ArbeidsmarktkwalificatieDetailedComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ArbeidsmarktkwalificatieDetailedComponent]
    })
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ArbeidsmarktkwalificatieDetailedComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

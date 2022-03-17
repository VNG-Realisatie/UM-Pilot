import {ComponentFixture, TestBed} from '@angular/core/testing';

import {ArbeidsmarktkwalificatieFormComponent} from './arbeidsmarktkwalificatie-form.component';

describe('ArbeidsmarktkwalificatieFormComponent', () => {
  let component: ArbeidsmarktkwalificatieFormComponent;
  let fixture: ComponentFixture<ArbeidsmarktkwalificatieFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ArbeidsmarktkwalificatieFormComponent]
    })
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ArbeidsmarktkwalificatieFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

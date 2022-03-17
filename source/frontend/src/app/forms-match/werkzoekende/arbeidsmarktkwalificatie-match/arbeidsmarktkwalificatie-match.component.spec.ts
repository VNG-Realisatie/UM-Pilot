import {ComponentFixture, TestBed} from '@angular/core/testing';

import {ArbeidsmarktkwalificatieMatchComponent} from './arbeidsmarktkwalificatie-match.component';

describe('ArbeidsmarktkwalificatieMatchComponent', () => {
  let component: ArbeidsmarktkwalificatieMatchComponent;
  let fixture: ComponentFixture<ArbeidsmarktkwalificatieMatchComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ArbeidsmarktkwalificatieMatchComponent]
    })
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ArbeidsmarktkwalificatieMatchComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

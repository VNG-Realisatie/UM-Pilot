import {ComponentFixture, TestBed} from '@angular/core/testing';

import {ArbeidsvoorwaardenFormComponent} from './arbeidsvoorwaarden-form.component';

describe('ArbeidsvoorwaardenFormComponent', () => {
  let component: ArbeidsvoorwaardenFormComponent;
  let fixture: ComponentFixture<ArbeidsvoorwaardenFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ArbeidsvoorwaardenFormComponent]
    })
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ArbeidsvoorwaardenFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

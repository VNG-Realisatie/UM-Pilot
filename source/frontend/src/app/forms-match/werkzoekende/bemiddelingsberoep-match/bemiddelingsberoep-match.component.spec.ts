import {ComponentFixture, TestBed} from '@angular/core/testing';

import {BemiddelingsberoepMatchComponent} from './bemiddelingsberoep-match.component';

describe('BemiddelingsberoepMatchComponent', () => {
  let component: BemiddelingsberoepMatchComponent;
  let fixture: ComponentFixture<BemiddelingsberoepMatchComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [BemiddelingsberoepMatchComponent]
    })
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(BemiddelingsberoepMatchComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

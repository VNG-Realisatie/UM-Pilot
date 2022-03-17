import {ComponentFixture, TestBed} from '@angular/core/testing';

import {BeroepMatchComponent} from './beroep-match.component';

describe('BeroepMatchComponent', () => {
  let component: BeroepMatchComponent;
  let fixture: ComponentFixture<BeroepMatchComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [BeroepMatchComponent]
    })
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(BeroepMatchComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

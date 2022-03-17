export function selectToTextConvert(value: string | number | undefined, values: Map<string | number, string>) {

  if (value != null && values.get(value) != null) {
    return values.get(value);
  } else {
    return value;
  }
}

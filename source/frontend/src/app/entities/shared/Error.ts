export interface Error {
  code: string;
  message: string;
  details?: string;
}

export function isError(object: any): object is Error {
  return 'code' in object && 'message' in object;
}

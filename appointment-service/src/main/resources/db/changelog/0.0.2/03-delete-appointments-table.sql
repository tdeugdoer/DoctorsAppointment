DELETE
FROM appointments
WHERE id IN ('a1b2c3d4-e5f6-7890-1234-567890abcdef', 'b2c3d4e5-f678-9012-3456-7890abcdef12',
             'c3d4e5f6-7890-1234-5678-90abcdef1234', 'd4e5f678-9012-3456-7890-abcdef123456',
             'e5f67890-1234-5678-90ab-cdef12345678');
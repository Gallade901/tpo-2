import pandas as pd
import matplotlib.pyplot as plt
import glob
import os
import numpy as np

plt.rcParams['figure.figsize'] = (4, 3)
plt.rcParams['figure.dpi'] = 100

csv_files = glob.glob('csv/*.csv')

for file_path in csv_files:
    try:
        df = pd.read_csv(file_path)
        if len(df.columns) < 2:
            print(f"Пропуск файла '{os.path.basename(file_path)}' - недостаточно столбцов")
            continue

        x_col, y_col = df.columns[0], df.columns[1]
        df_clean = df.replace('undefined', np.nan)

        try:
            x = pd.to_numeric(df_clean[x_col])
            y = pd.to_numeric(df_clean[y_col])

            fig = plt.figure(os.path.splitext(os.path.basename(file_path))[0])
            ax = fig.add_subplot(111)

            ax.plot(x, y, linestyle='', marker='o', markersize=1)

            x_range = x.max() - x.min()
            padding_x = x_range * 0.05
            ax.set_xlim(x.min() - padding_x, x.max() + padding_x)

            ax.set_ylim(-10, 10)

            ax.set_title(os.path.splitext(os.path.basename(file_path))[0], fontsize=10)
            ax.set_xlabel(x_col, fontsize=9)
            ax.set_ylabel(y_col, fontsize=9)

            ax.grid(True, linestyle='--', alpha=0.6)
            ax.xaxis.set_major_locator(plt.MaxNLocator(5))
            ax.yaxis.set_major_locator(plt.MaxNLocator(5))

            fig.tight_layout()

        except ValueError as ve:
            print(f"Ошибка данных в '{os.path.basename(file_path)}': {ve}")

    except Exception as e:
        print(f"Ошибка обработки '{os.path.basename(file_path)}': {e}")

plt.show()
print("Обработка завершена.")